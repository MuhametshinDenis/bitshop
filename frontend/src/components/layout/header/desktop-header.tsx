import {
  CircleUser,
  Heart,
  LayoutGrid,
  Package,
  Search,
  ShoppingCart,
} from "lucide-react"
import { Input } from "@/components/ui/input"
import { ROUTES } from "@/constants/routes"
import Link from "next/link"
import { useSidebar } from "@/components/ui/sidebar"
import { useUserStore } from "@/store/userStore"

export const headerItems = [
  { label: "Профиль", link: ROUTES.PROFILE, icon: CircleUser },
  { label: "Заказы", link: ROUTES.ORDERS, icon: Package },
  { label: "Избранное", link: ROUTES.FAVORITES, icon: Heart },
  { label: "Корзина", link: ROUTES.CART, icon: ShoppingCart },
]

export function DesktopHeader() {
  const { toggleSidebar } = useSidebar()
  const { user } = useUserStore()

  return (
    <header className="flex items-center justify-between py-2 gap-3">
      <div className="flex items-center gap-3">
        <h1 className="font-bold text-2xl">BitShop</h1>
        <div
          role="button"
          className="flex items-center bg-primary text-primary-foreground p-2 gap-2 rounded-lg transition-all duration-200 hover:opacity-90 hover:cursor-pointer"
          onClick={toggleSidebar}
        >
          <LayoutGrid size={20} />
          <p className="font-semibold">Каталог</p>
        </div>
      </div>
      <div className="w-full flex items-center gap-1">
        <Input placeholder="Искать на BitShop" className="h-10" />
        <div
          role="button"
          className="flex items-center bg-primary text-primary-foreground p-2 gap-2 rounded-lg transition-all duration-200 hover:opacity-90 hover:cursor-pointer"
        >
          <Search size={20} />
          <p className="font-semibold">Искать</p>
        </div>
      </div>
      <div className="flex items-center gap-3 text-muted-foreground">
        {headerItems.map(({ label, link, icon: Icon }) => (
          <Link
            href={link}
            key={label}
            className="transition-all duration-200 hover:text-primary"
          >
            <Icon size={20} className="w-full" />
            <p className="text-sm">{label}</p>
          </Link>
        ))}
      </div>
    </header>
  )
}
